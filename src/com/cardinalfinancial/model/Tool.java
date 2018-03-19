package com.cardinalfinancial.model;

public class Tool {

    public static final int TOOL_TYPE_LADDER = 1;
    public static final int TOOL_TYPE_CHAINSAW = 2;
    public static final int TOOL_TYPE_JACKHAMMER = 3;

    public static final String BRAND_WERNER = "Werner";
    public static final String BRAND_STIHL = "Stihl";
    public static final String BRAND_RIDGID = "Ridgid";
    public static final String BRAND_DEWALT = "De Walt";

    public static final String TOOL_CODE_LADW = "LADW";
    public static final String TOOL_CODE_CHNS = "CHNS";
    public static final String TOOL_CODE_JAKR = "JAKR";
    public static final String TOOL_CODE_JAKD = "JAKD";

    private String toolCode;        //contains the code specifying what tool the instance is
    private String brand;           //contains the brand of tool the instance is
    private int toolType;           //contains the tool type code of the instance

    public Tool(String toolCode){
        this.toolCode = toolCode;
        switch (toolCode){
            case TOOL_CODE_LADW:
                this.brand = BRAND_WERNER;
                this.toolType = TOOL_TYPE_LADDER;
                break;
            case TOOL_CODE_CHNS:
                this.brand = BRAND_STIHL;
                this.toolType = TOOL_TYPE_CHAINSAW;
                break;
            case TOOL_CODE_JAKR:
                this.brand = BRAND_RIDGID;
                this.toolType = TOOL_TYPE_JACKHAMMER;
                break;
            case TOOL_CODE_JAKD:
                this.brand = BRAND_DEWALT;
                this.toolType = TOOL_TYPE_JACKHAMMER;
                break;
        }
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getToolType() {
        return toolType;
    }

    public void setToolType(int toolType) {
        this.toolType = toolType;
    }
}
