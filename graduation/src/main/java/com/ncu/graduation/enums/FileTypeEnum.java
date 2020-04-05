package com.ncu.graduation.enums;


public enum FileTypeEnum {
    PROJECT("project","D://FinallySchool/project/"),
    OPENING_REPORT("openReport","D://FinallySchool/openingReport/"),
    TASK_BOOK("taskBook","D://FinallySchool/taskBook/"),
    BULLETIN("bulletin","D://FinallySchool/bulletin/"),
    THESIS("thesis","D://FinallySchool/thesis/"),
    FOREIGNFILE("foreignFile","D://FinallySchool/foreignFile/"),
    TRANSLATIONFILE("translationFile","D://FinallySchool/translationFile/")
    ;


    private String type;
    private String preUrl;

    FileTypeEnum(String type, String preUrl) {
        this.type = type;
        this.preUrl = preUrl;
    }

    public String getType() {
        return type;
    }

    public String getPreUrl() {
        return preUrl;
    }

    static public FileTypeEnum judgeType(String type){
        for (FileTypeEnum value : FileTypeEnum.values()) {
            if (type.equals(value.getType())){
                return value;
            }
        }
        return null;
    }

}
