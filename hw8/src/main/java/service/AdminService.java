package service;

import entity.Admin;
import repository.AdminRepository;

import java.sql.SQLException;
import java.util.List;

public class AdminService implements Service<Admin> {
    AdminRepository adminRepository = new AdminRepository();


    @Override
    public int add(Admin admin) {
        try {
            return adminRepository.add(admin);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    @Override
    public List<Admin> findAll() {
            return null;
    }

    @Override
    public int update(Admin admin) {
        try {
            return adminRepository.update(admin);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
