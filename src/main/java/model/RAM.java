package model;

import java.util.Objects;

public class RAM {
    private int id;
    private String name;
    private double rating;
    private double price;
    private String shop_URL;
    private String image_URL;
    private int tdp;
    private String type;
    private int clock;

    public RAM() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShop_URL() {
        return shop_URL;
    }

    public void setShop_URL(String shop_URL) {
        this.shop_URL = shop_URL;
    }

    public String getImage_URL() {
        return image_URL;
    }

    public void setImage_URL(String image_URL) {
        this.image_URL = image_URL;
    }

    public int getTdp() {
        return tdp;
    }

    public void setTdp(int tdp) {
        this.tdp = tdp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RAM)) return false;
        RAM ram = (RAM) o;
        return Double.compare(getRating(), ram.getRating()) == 0 && Double.compare(getPrice(), ram.getPrice()) == 0 && getTdp() == ram.getTdp() && getClock() == ram.getClock() && Objects.equals(getName(), ram.getName()) && Objects.equals(getShop_URL(), ram.getShop_URL()) && Objects.equals(getImage_URL(), ram.getImage_URL()) && Objects.equals(getType(), ram.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRating(), getPrice(), getShop_URL(), getImage_URL(), getTdp(), getType(), getClock());
    }

    @Override
    public String toString() {
        return "RAM{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", shop_URL='" + shop_URL + '\'' +
                ", image_URL='" + image_URL + '\'' +
                ", tdp=" + tdp +
                ", type='" + type + '\'' +
                ", clock=" + clock +
                '}';
    }
}
