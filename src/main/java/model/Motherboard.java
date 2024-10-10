package model;

import java.util.Objects;

public class Motherboard {
    private int id;
    private String name;
    private double rating;
    private double price;
    private String shop_URL;
    private String image_URL;
    private int tdp;
    private String socket;
    private String chipset;
    private String ram_type;
    private int ram_max_speed;
    private int ram_slot;
    private int ram_max;
    private int pcie_x16_slot;
    private int pcie_x1_slot;
    private int m2_slot;
    private int sata_slot;
    private String lan;
    private String wifi;
    private String form_factor;

    public Motherboard() {
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

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getRam_type() {
        return ram_type;
    }

    public void setRam_type(String ram_type) {
        this.ram_type = ram_type;
    }

    public int getRam_max_speed() {
        return ram_max_speed;
    }

    public void setRam_max_speed(int ram_max_speed) {
        this.ram_max_speed = ram_max_speed;
    }

    public int getRam_slot() {
        return ram_slot;
    }

    public void setRam_slot(int ram_slot) {
        this.ram_slot = ram_slot;
    }

    public int getRam_max() {
        return ram_max;
    }

    public void setRam_max(int ram_max) {
        this.ram_max = ram_max;
    }

    public int getPcie_x16_slot() {
        return pcie_x16_slot;
    }

    public void setPcie_x16_slot(int pcie_x16_slot) {
        this.pcie_x16_slot = pcie_x16_slot;
    }

    public int getPcie_x1_slot() {
        return pcie_x1_slot;
    }

    public void setPcie_x1_slot(int pcie_x1_slot) {
        this.pcie_x1_slot = pcie_x1_slot;
    }

    public int getM2_slot() {
        return m2_slot;
    }

    public void setM2_slot(int m2_slot) {
        this.m2_slot = m2_slot;
    }

    public int getSata_slot() {
        return sata_slot;
    }

    public void setSata_slot(int sata_slot) {
        this.sata_slot = sata_slot;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getForm_factor() {
        return form_factor;
    }

    public void setForm_factor(String form_factor) {
        this.form_factor = form_factor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Motherboard)) return false;
        Motherboard that = (Motherboard) o;
        return Double.compare(getRating(), that.getRating()) == 0 && Double.compare(getPrice(), that.getPrice()) == 0 && getTdp() == that.getTdp() && getRam_max_speed() == that.getRam_max_speed() && getRam_slot() == that.getRam_slot() && getRam_max() == that.getRam_max() && getPcie_x16_slot() == that.getPcie_x16_slot() && getPcie_x1_slot() == that.getPcie_x1_slot() && getM2_slot() == that.getM2_slot() && getSata_slot() == that.getSata_slot() && Objects.equals(getName(), that.getName()) && Objects.equals(getShop_URL(), that.getShop_URL()) && Objects.equals(getImage_URL(), that.getImage_URL()) && Objects.equals(getSocket(), that.getSocket()) && Objects.equals(getChipset(), that.getChipset()) && Objects.equals(getRam_type(), that.getRam_type()) && Objects.equals(getLan(), that.getLan()) && Objects.equals(getWifi(), that.getWifi()) && Objects.equals(getForm_factor(), that.getForm_factor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRating(), getPrice(), getShop_URL(), getImage_URL(), getTdp(), getSocket(), getChipset(), getRam_type(), getRam_max_speed(), getRam_slot(), getRam_max(), getPcie_x16_slot(), getPcie_x1_slot(), getM2_slot(), getSata_slot(), getLan(), getWifi(), getForm_factor());
    }

    @Override
    public String toString() {
        return "Motherboard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", shop_URL='" + shop_URL + '\'' +
                ", image_URL='" + image_URL + '\'' +
                ", tdp=" + tdp +
                ", socket='" + socket + '\'' +
                ", chipset='" + chipset + '\'' +
                ", ram_type='" + ram_type + '\'' +
                ", ram_max_speed=" + ram_max_speed +
                ", ram_slot=" + ram_slot +
                ", ram_max=" + ram_max +
                ", pcie_x16_slot=" + pcie_x16_slot +
                ", pcie_x1_slot=" + pcie_x1_slot +
                ", m2_slot=" + m2_slot +
                ", sata_slot=" + sata_slot +
                ", lan='" + lan + '\'' +
                ", wifi='" + wifi + '\'' +
                ", form_factor='" + form_factor + '\'' +
                '}';
    }
}
