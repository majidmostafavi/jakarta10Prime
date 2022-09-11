package com.majidmostafavi.jakartaee10prime12.core.enumration;

/**
 * Created by majid on 7/12/16.
 */
public enum EntityType {
    Tree("Tree", "message.general.entityType.tree"),
    Table("Table", "message.general.entityType.table");

    String key;
    String label;

    private EntityType(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public static EntityType get(String key) {
        if(key!=null){
            if(key.equals(Tree.getKey())){
                return Tree;
            }else if(key.equals(Table.getKey())){
                return Table;
            }
        }
        return null;
    }

}
