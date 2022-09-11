package com.majidmostafavi.jakartaee10prime12.core.enumration;

import com.majidmostafavi.jakartaee10prime12.core.interfaces.Enumable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by majid on 5/22/17.
 */
public enum SubSystem implements Enumable {
    Accounting("Accounting", "message.general.usecaseType.Accounting",true),
    Research("Research", "message.general.usecaseType.Research",false),
    Inventory("Inventory", "message.general.usecaseType.Inventory",true),
    Treasury("Treasury", "message.general.usecaseType.Treasury",true),
    Salary("Salary", "message.general.usecaseType.Salary",true),
    Asset("Asset", "message.general.usecaseType.Asset",true),
    Warranty("Warranty", "message.general.usecaseType.Warranty",true),
    Budget("Budget", "message.general.usecaseType.Budget",true),
    Credit("Credit", "message.general.usecaseType.Credit",true),
    Revenue("Revenue", "message.general.usecaseType.Revenue",true),
    Tender("Tender", "message.general.usecaseType.Tender",false),
    Contract("Contract", "message.general.usecaseType.Contract",true),
    HumanResource("HumanResource", "message.general.usecaseType.HumanResource",false),
    Catering("Catering", "message.general.usecaseType.Catering",false),
    Documentation("Documentation", "message.general.usecaseType.Documentation",false),
    Common("Common", "message.general.usecaseType.common",false),
    Facility("Facility", "message.general.usecaseType.facility",false),
    Transportation("Transportation", "message.general.usecaseType.transportation",false),
    Moheb("Moheb", "message.general.usecaseType.moheb",true),
    ComplaintHandling("ComplaintHandling", "message.general.usecaseType.complaintHandling",false),
    SpecialReport("SpecialReport", "message.general.usecaseType.specialReport",false);


    private final String key;
    private final String label;
    private final boolean accounting;

    SubSystem(String key, String label,boolean accounting) {
        this.key = key;
        this.label = label;
        this.accounting = accounting;
    }

    public String getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    public boolean isAccounting() {
        return accounting;
    }

    @Override
    public int getOrdinal(String key) {

        if (key.equals(Accounting.getKey())) {
            return SubSystem.Accounting.ordinal();
        } else if (key.equals(Asset.getKey())) {
            return SubSystem.Asset.ordinal();
        } else if (key.equals(Budget.getKey())) {
            return SubSystem.Budget.ordinal();
        } else if (key.equals(Contract.getKey())) {
            return SubSystem.Contract.ordinal();
        } else if (key.equals(Credit.getKey())) {
            return SubSystem.Credit.ordinal();
        } else if (key.equals(Documentation.getKey())) {
            return SubSystem.Documentation.ordinal();
        } else if (key.equals(HumanResource.getKey())) {
            return SubSystem.HumanResource.ordinal();
        } else if (key.equals(Inventory.getKey())) {
            return SubSystem.Inventory.ordinal();
        } else if (key.equals(Revenue.getKey())) {
            return SubSystem.Revenue.ordinal();
        } else if (key.equals(Salary.getKey())) {
            return SubSystem.Salary.ordinal();
        } else if (key.equals(Tender.getKey())) {
            return SubSystem.Tender.ordinal();
        } else if (key.equals(Treasury.getKey())) {
            return SubSystem.Treasury.ordinal();
        } else if (key.equals(Warranty.getKey())) {
            return SubSystem.Warranty.ordinal();
        } else if (key.equals(Research.getKey())) {
            return SubSystem.Research.ordinal();
        } else if (key.equals(Common.getKey())) {
            return SubSystem.Common.ordinal();
        } else if (key.equals(Facility.getKey())) {
            return SubSystem.Facility.ordinal();
        } else if (key.equals(Transportation.getKey())) {
            return SubSystem.Transportation.ordinal();
        } else if (key.equals(Moheb.getKey())) {
            return SubSystem.Moheb.ordinal();
        } else if (key.equals(ComplaintHandling.getKey())) {
            return SubSystem.ComplaintHandling.ordinal();
        }
        else return SubSystem.SpecialReport.ordinal();
    }


    public static SubSystem getValue(String key) {
        if (key != null) {
            if (key.equals(Accounting.getKey())) {
                return Accounting;
            } else if (key.equals(Asset.getKey())) {
                return Asset;
            } else if (key.equals(Budget.getKey())) {
                return Budget;
            } else if (key.equals(Contract.getKey())) {
                return Contract;
            } else if (key.equals(Credit.getKey())) {
                return Credit;
            } else if (key.equals(Documentation.getKey())) {
                return Documentation;
            } else if (key.equals(HumanResource.getKey())) {
                return HumanResource;
            } else if (key.equals(Inventory.getKey())) {
                return Inventory;
            } else if (key.equals(Revenue.getKey())) {
                return Revenue;
            } else if (key.equals(Salary.getKey())) {
                return Salary;
            } else if (key.equals(Tender.getKey())) {
                return Tender;
            } else if (key.equals(Treasury.getKey())) {
                return Treasury;
            } else if (key.equals(Warranty.getKey())) {
                return Warranty;
            } else if (key.equals(Research.getKey())) {
                return Research;
            } else if (key.equals(Common.getKey())) {
                return Common;
            } else if (key.equals(Facility.getKey())) {
                return Facility;
            } else if (key.equals(Transportation.getKey())) {
                return Transportation;
            } else if (key.equals(Moheb.getKey())) {
                return Moheb;
            } else if (key.equals(ComplaintHandling.getKey())) {
                return ComplaintHandling;
            } else if (key.equals(SpecialReport.getKey())) {
                return SpecialReport;
            }
        }
        return null;
    }


    public static List<SubSystem> getAllSubSystem() {
        return Arrays.asList(values());
    }

    public static List<SubSystem> findAccounting(){
        List<SubSystem> subSystems = new ArrayList<>(0);
        for(SubSystem subSystem : values()){
            if(subSystem.isAccounting()){
                subSystems.add(subSystem);
            }
        }
        return subSystems;
    }

    public static SubSystem findByOrdinal(int ordinal){
        for(SubSystem subSystem : values()){
            if(subSystem.ordinal() == ordinal){
                return subSystem;
            }
        }
        return null;
    }
}
