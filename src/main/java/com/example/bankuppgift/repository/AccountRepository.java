package com.example.bankuppgift.repository;

import com.example.bankuppgift.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>
{

    @Query("SELECT a FROM Account a where LOWER(a.ownerName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Account> searchByName(@Param("keyword") String keyword);


    @Query("Select a FROM Account a where a.balance > :minBalance")
    Page<Account> findByMinBalance(@Param("minBalance") double minBalance, Pageable pageable);
}
