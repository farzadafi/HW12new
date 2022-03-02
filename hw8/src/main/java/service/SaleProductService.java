package service;

import entity.SaleProduct;
import repository.SaleProductRepository;

import java.sql.SQLException;
import java.util.List;

public class SaleProductService implements Service<SaleProduct> {
    private SaleProductRepository saleProductRepository = new SaleProductRepository();


    @Override
    public int add(SaleProduct saleProduct) {
        return saleProductRepository.add(saleProduct);
    }

    @Override
    public List<SaleProduct> findAll() {
        return null;
    }

    @Override
    public int update(SaleProduct saleProduct) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    public List<SaleProduct> saleProductByCustomerId(int id){
        return saleProductRepository.saleProductByCustomerId(id);
    }

    public List<SaleProduct> saleProductByAdminId(int id) {
        try {
            return saleProductRepository.saleProductByAdminId(id);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
