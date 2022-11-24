package VehicleRentalSystem.database;

import VehicleRentalSystem.model.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchDatabase {

    private final List<Branch> branches = new ArrayList<>();

    private static BranchDatabase INSTANCE = null;

    public static BranchDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BranchDatabase();
        }
        return INSTANCE;
    }

    public void insertBranch(String branchName) {
        branches.add(new Branch(branchName));
    }

    public Branch getBranchByName(String branchName) {
        return branches.stream()
                .filter(branch -> branch.getName().equals(branchName))
                .findAny()
                .orElse(null);
    }

    public List<Branch> getBranches() {
        return branches;
    }
}
