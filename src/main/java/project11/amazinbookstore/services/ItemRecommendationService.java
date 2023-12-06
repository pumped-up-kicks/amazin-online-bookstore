package project11.amazinbookstore.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.model.PurchasedItem;
import project11.amazinbookstore.model.RegisteredUser;

import java.util.*;

@Service
@Slf4j
public class ItemRecommendationService {
    private final PurchasedItemService purchasedItemService;
    private final UserService userService;

    public ItemRecommendationService(PurchasedItemService purchasedItemService, UserService userService){
        this.purchasedItemService = purchasedItemService;
        this.userService = userService;
    }

    private HashSet<String> intersection(HashSet<String> a, HashSet<String> b)
    {

        // Find the intersection of the two sets
        HashSet<String> intersect = new HashSet<>();
        for (String n: a)
        {
            if (b.contains(n))
                intersect.add(n);
        }

        return intersect;
    }

    private double jaccardIndex(HashSet<String> s1, HashSet<String> s2)
    {
        // Prevent users with exactly the same purchases (and nothing new) from being chosen.
        if (s1.equals(s2)) {
            return 0;
        }

        // Sizes of both the sets
        int sizeS1 = s1.size();
        int sizeS2 = s2.size();

        // Get the intersection set
        HashSet<String> intersect = intersection(s1, s2);

        // Size of the intersection set
        int sizeIntersection = intersect.size();

        // Calculate the Jaccard index
        double jaccardIndex  = (double)sizeIntersection / (double)(sizeS1 + sizeS2 - sizeIntersection);

        // Return the Jaccard index
        return jaccardIndex;
    }

    private double jaccardDistance(double jaccardIndex)
    {
        // Calculate the Jaccard distance
        double jaccardDistance = 1 - jaccardIndex;

        // Return the Jaccard distance
        return jaccardDistance;
    }

    public List<Book> getRecommendation(String name){
        RegisteredUser targetUser = userService.findUserByUserName(name);
        List<RegisteredUser> allUserList = userService.findAllUser();
        List<PurchasedItem> targetUserPurchaseHistory = purchasedItemService.findAllPurchasedItems(name);

        HashSet<String> targetUserBookISBN = new HashSet<>();
        for(PurchasedItem item: targetUserPurchaseHistory){
            targetUserBookISBN.add(item.getBook().getIsbn());
        }

        Map<RegisteredUser, Double> allUserJaccardDistanceToTarget = new HashMap<>();

        for (RegisteredUser user: allUserList){
            if (!Objects.equals(user.getId(), targetUser.getId())){

                List<PurchasedItem> purchasedItemList = user.getPurchasedItemList();

                HashSet<String> userBookISBN = new HashSet<>();
                for(PurchasedItem item: purchasedItemList){
                    userBookISBN.add(item.getBook().getIsbn());
                }

                double jaccardIndex = jaccardIndex(targetUserBookISBN, userBookISBN);
                Double jaccardDistance = jaccardDistance(jaccardIndex);

                allUserJaccardDistanceToTarget.put(user, jaccardDistance);
            }
        }

        Double shortestJaccardDistanceToTarget = 1D;
        List<RegisteredUser> mostSimilarUsersComparedToTarget = new ArrayList<>();
        List<Book> recommendedBookList = new ArrayList<>();

        for (Map.Entry<RegisteredUser, Double> entry : allUserJaccardDistanceToTarget.entrySet()) {
            if (Math.abs(entry.getValue() - shortestJaccardDistanceToTarget) < 0.001) {
                mostSimilarUsersComparedToTarget.add(entry.getKey());
            } else if (entry.getValue() < shortestJaccardDistanceToTarget){
                shortestJaccardDistanceToTarget = entry.getValue();
                mostSimilarUsersComparedToTarget = new ArrayList<>();
                mostSimilarUsersComparedToTarget.add(entry.getKey());
            }
        }

        // If there is not even a single similar user, return an empty list
        if (mostSimilarUsersComparedToTarget.isEmpty()){
            return recommendedBookList;
        }

        List<Book> mostSimilarUserPurchasedBook = new ArrayList<>();
        for (RegisteredUser mostSimilarUserComparedToTarget : mostSimilarUsersComparedToTarget) {
            List<PurchasedItem> mostSimilarUserPurchaseHistory = mostSimilarUserComparedToTarget.getPurchasedItemList();
            for (PurchasedItem item : mostSimilarUserPurchaseHistory) {
                mostSimilarUserPurchasedBook.add(item.getBook());
            }
        }

        List<Book> targetUserPurchasedBook = new ArrayList<>();
        for (PurchasedItem targetItem: targetUserPurchaseHistory){
            targetUserPurchasedBook.add(targetItem.getBook());
        }

        recommendedBookList = mostSimilarUserPurchasedBook;
        recommendedBookList.removeAll(targetUserPurchasedBook);

        return recommendedBookList;
    }
}
