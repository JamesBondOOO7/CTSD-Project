package com.spectre.school_app.repository;

import com.spectre.school_app.model.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer>, CrudRepository<Contact, Integer> {

    /*
        `Derived Query Method`
        i.e. the MySQL query will be automatically generated.
        We just need to define this abstract method
     */
    List<Contact> findByStatus(String status);

    @Query("SELECT c FROM Contact c WHERE c.status = :status")  // JPQL statement
    // @Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status", nativeQuery = true)  // native SQL query
    Page<Contact> findByStatusWithQuery(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
    int updateStatusById(String status, int id);

    /*
        For `NamedQueries`
        - All the queries are maintained within the specific model (entity) itself
        - supports dynamic sorting
     */
    Page<Contact> findOpenMsgs(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    int updateMsgStatus(String status, int id);

    /*
        For `NamedNativeQueries`
        - All the queries are maintained within the specific model (entity) itself
        - Doesn't support dynamic sorting
     */
    @Query(nativeQuery = true)
    Page<Contact> findOpenMsgsNative(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
    int updateMsgStatusNative(String status, int id);
}
