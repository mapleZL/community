package com.phkj.entity.member;

import java.io.Serializable;

/**
 * @author 
 */
public class StAppletPetWithBLOBs extends StAppletPet implements Serializable {
    /**
     * 健康免检证书
     */
    private String exemptionImg;

    /**
     * 养犬登记证书
     */
    private String petRegisImg;

    /**
     * 宠物照片
     */
    private String petImg;

    private static final long serialVersionUID = 1L;

    public String getExemptionImg() {
        return exemptionImg;
    }

    public void setExemptionImg(String exemptionImg) {
        this.exemptionImg = exemptionImg;
    }

    public String getPetRegisImg() {
        return petRegisImg;
    }

    public void setPetRegisImg(String petRegisImg) {
        this.petRegisImg = petRegisImg;
    }

    public String getPetImg() {
        return petImg;
    }

    public void setPetImg(String petImg) {
        this.petImg = petImg;
    }
}