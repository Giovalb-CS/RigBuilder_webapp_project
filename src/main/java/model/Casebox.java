package model;

import java.util.Objects;

public class Casebox {
    private int id;
    private String name;
    private double rating;
    private double price;
    private String shop_URL;
    private String image_URL;
    private int max_cooler_height;
    private int radiator_size;
    private int gpu_lenght;
    private String form_factor;
    private int psu_lenght;
    private int pcie_slots;

    public Casebox() {
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

    public int getMax_cooler_height() {
        return max_cooler_height;
    }

    public void setMax_cooler_height(int max_cooler_height) {
        this.max_cooler_height = max_cooler_height;
    }

    public int getRadiator_size() {
        return radiator_size;
    }

    public void setRadiator_size(int radiator_size) {
        this.radiator_size = radiator_size;
    }

    public int getGpu_lenght() {
        return gpu_lenght;
    }

    public void setGpu_lenght(int gpu_lenght) {
        this.gpu_lenght = gpu_lenght;
    }

    public String getForm_factor() {
        return form_factor;
    }

    public void setForm_factor(String form_factor) {
        this.form_factor = form_factor;
    }

    public int getPsu_lenght() {
        return psu_lenght;
    }

    public void setPsu_lenght(int psu_lenght) {
        this.psu_lenght = psu_lenght;
    }

    public int getPcie_slots() {
        return pcie_slots;
    }

    public void setPcie_slots(int pcie_slots) {
        this.pcie_slots = pcie_slots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Casebox)) return false;
        Casebox casebox = (Casebox) o;
        return Double.compare(getRating(), casebox.getRating()) == 0 && Double.compare(getPrice(), casebox.getPrice()) == 0 && getMax_cooler_height() == casebox.getMax_cooler_height() && getRadiator_size() == casebox.getRadiator_size() && getGpu_lenght() == casebox.getGpu_lenght() && getPsu_lenght() == casebox.getPsu_lenght() && getPcie_slots() == casebox.getPcie_slots() && Objects.equals(getName(), casebox.getName()) && Objects.equals(getShop_URL(), casebox.getShop_URL()) && Objects.equals(getImage_URL(), casebox.getImage_URL()) && Objects.equals(getForm_factor(), casebox.getForm_factor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRating(), getPrice(), getShop_URL(), getImage_URL(), getMax_cooler_height(), getRadiator_size(), getGpu_lenght(), getForm_factor(), getPsu_lenght(), getPcie_slots());
    }

    @Override
    public String toString() {
        return "Casebox{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", shop_URL='" + shop_URL + '\'' +
                ", image_URL='" + image_URL + '\'' +
                ", max_cooler_height=" + max_cooler_height +
                ", radiator_size=" + radiator_size +
                ", gpu_lenght=" + gpu_lenght +
                ", form_factor='" + form_factor + '\'' +
                ", psu_lenght=" + psu_lenght +
                ", pcie_slots=" + pcie_slots +
                '}';
    }
}
