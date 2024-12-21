package model;

import java.util.Objects;

public class SSD {
    private int id;
    private String name;
    private double rating;
    private double price;
    private String shop_URL;
    private String image_URL;
    private int tdp;
    private String pcie_gen;
    private String capacity;
    private int speed_read;
    private int speed_write;

    public SSD() {
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

    public String getPcie_gen() {
        return pcie_gen;
    }

    public void setPcie_gen(String pcie_gen) {
        this.pcie_gen = pcie_gen;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public int getSpeed_read() {
        return speed_read;
    }

    public void setSpeed_read(int speed_read) {
        this.speed_read = speed_read;
    }

    public int getSpeed_write() {
        return speed_write;
    }

    public void setSpeed_write(int speed_write) {
        this.speed_write = speed_write;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SSD)) return false;
        SSD ssd = (SSD) o;
        return Double.compare(getRating(), ssd.getRating()) == 0 && Double.compare(getPrice(), ssd.getPrice()) == 0 && getTdp() == ssd.getTdp() && getSpeed_read() == ssd.getSpeed_read() && getSpeed_write() == ssd.getSpeed_write() && Objects.equals(getName(), ssd.getName()) && Objects.equals(getShop_URL(), ssd.getShop_URL()) && Objects.equals(getImage_URL(), ssd.getImage_URL()) && Objects.equals(getPcie_gen(), ssd.getPcie_gen()) && Objects.equals(getCapacity(), ssd.getCapacity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRating(), getPrice(), getShop_URL(), getImage_URL(), getTdp(), getPcie_gen(), getCapacity(), getSpeed_read(), getSpeed_write());
    }

    @Override
    public String toString() {
        return "SSD{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", shop_URL='" + shop_URL + '\'' +
                ", image_URL='" + image_URL + '\'' +
                ", tdp=" + tdp +
                ", pcie_gen='" + pcie_gen + '\'' +
                ", capacity='" + capacity + '\'' +
                ", speed_read=" + speed_read +
                ", speed_write=" + speed_write +
                '}';
    }
}
