package project11.amazinbookstore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerUser extends RegisteredUser{

    @Id
    private Long id;
    private Role role;

    @Column(name = "cart")
    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customer")
    //@JoinColumn(name="cart_item_id", referencedColumnName="id")
    @JsonManagedReference
    private List<CartItem> cartItem = new ArrayList<>();

    public CustomerUser() {
        super();
        this.role = Role.USER;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }
}
