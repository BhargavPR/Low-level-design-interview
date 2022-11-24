package VehicleRentalSystem.service;

import VehicleRentalSystem.database.BranchDatabase;

public class BranchService {

    private BranchDatabase branchDatabase;

    public BranchService(BranchDatabase branchDatabase) {
        this.branchDatabase = branchDatabase;
    }

    public void insertBranch(String branchName) {
        branchDatabase.insertBranch(branchName);
    }
}
