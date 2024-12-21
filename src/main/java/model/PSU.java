package model;

import java.util.Objects;

public class PSU {
    private int id;
    private String name;
    private double rating;
    private double price;
    private String shop_URL;
    private String image_URL;
    private String type;
    private String efficiency;
    private int wattage;
    private int lenght;

    public PSU() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public int getWattage() {
        return wattage;
    }

    public void setWattage(int wattage) {
        this.wattage = wattage;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    @Override
    public String toString() {
        return "PSU{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", shop_URL='" + shop_URL + '\'' +
                ", image_URL='" + image_URL + '\'' +
                ", type='" + type + '\'' +
                ", efficiency='" + efficiency + '\'' +
                ", wattage=" + wattage +
                ", lenght=" + lenght +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PSU)) return false;
        PSU psu = (PSU) o;
        return getId() == psu.getId() && Double.compare(getRating(), psu.getRating()) == 0 && Double.compare(getPrice(), psu.getPrice()) == 0 && getWattage() == psu.getWattage() && getLenght() == psu.getLenght() && Objects.equals(getName(), psu.getName()) && Objects.equals(getShop_URL(), psu.getShop_URL()) && Objects.equals(getImage_URL(), psu.getImage_URL()) && Objects.equals(getType(), psu.getType()) && Objects.equals(getEfficiency(), psu.getEfficiency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRating(), getPrice(), getShop_URL(), getImage_URL(), getType(), getEfficiency(), getWattage(), getLenght());
    }
}
