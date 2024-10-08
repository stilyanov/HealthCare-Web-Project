package bg.softuni.healthcare.model.entity.enums;

import lombok.Getter;

@Getter
public enum DepartmentEnum {
    CARDIOLOGY("Cardiology"),
    DERMATOLOGY("Dermatology"),
    NEUROLOGY("Neurology"),
    ORTHOPEDICS("Orthopedics"),
    PEDIATRICS("Pediatrics"),
    GENERAL_SURGERY("General Surgery"),
    GYNECOLOGY("Gynecology"),
    ONCOLOGY("Oncology"),
    PSYCHIATRY("Psychiatry"),
    RADIOLOGY("Radiology"),
    ENDOCRINOLOGY("Endocrinology");

    private final String displayName;

    DepartmentEnum(String name) {
        this.displayName = name;
    }

}
