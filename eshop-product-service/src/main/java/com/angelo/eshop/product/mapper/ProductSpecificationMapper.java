package com.angelo.eshop.product.mapper;

import com.angelo.eshop.product.model.ProductSpecification;
import org.apache.ibatis.annotations.*;


@Mapper
public interface ProductSpecificationMapper {

    @Insert("INSERT INTO product_specification(name,value,product_id) VALUES(#{name},#{value},#{productId})")
    public void add(ProductSpecification productSpecification);

    @Update("UPDATE product_specification SET name=#{name},value=#{value},product_id=#{productId} WHERE id=#{id}")
    public void update(ProductSpecification productSpecification);

    @Delete("DELETE FROM product_specification WHERE id=#{id}")
    public void delete(Long id);

    @Select("SELECT * FROM product_specification WHERE id=#{id}")
    public ProductSpecification findById(Long id);

    @Select("SELECT * FROM product_specification WHERE product_id=#{productId}")
    public ProductSpecification findByProductId(@Param("productId") Long productId);

}
