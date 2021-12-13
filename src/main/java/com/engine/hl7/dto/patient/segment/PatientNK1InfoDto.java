package com.engine.hl7.dto.patient.segment;

public class PatientNK1InfoDto {
    private String familyName;
    private String givenName;
    private String initialsThereof;
    private String relationship;
    private String streetAddress;

    private PatientNK1InfoDto() {
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getInitialsThereof() {
        return initialsThereof;
    }

    public String getRelationship() {
        return relationship;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public static final class Builder {
        private String familyName;
        private String givenName;
        private String initialsThereof;
        private String relationship;
        private String streetAddress;

        public Builder familyName(String familyName) {
            this.familyName = familyName;
            return this;
        }

        public Builder givenName(String givenName) {
            this.givenName = givenName;
            return this;
        }

        public Builder initialsThereof(String initialsThereof) {
            this.initialsThereof = initialsThereof;
            return this;
        }

        public Builder relationship(String relationship) {
            this.relationship = relationship;
            return this;
        }

        public Builder streetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
            return this;
        }

        public PatientNK1InfoDto build() {
            PatientNK1InfoDto patientNK1InfoDto = new PatientNK1InfoDto();

            patientNK1InfoDto.familyName = this.familyName;
            patientNK1InfoDto.givenName = this.givenName;
            patientNK1InfoDto.initialsThereof = this.initialsThereof;
            patientNK1InfoDto.relationship = this.relationship;
            patientNK1InfoDto.streetAddress = this.streetAddress;

            return patientNK1InfoDto;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
