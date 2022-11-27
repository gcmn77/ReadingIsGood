package example.micronaut.readingisgood.repository;

import example.micronaut.readingisgood.entity.Book;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@JdbcRepository(dialect = Dialect.H2)
public interface BookRepository extends CrudRepository<Book, Long> {
    @Transactional
    @Query("UPDATE Book SET stock = :stock WHERE id = :id")
    boolean updateStockById(Long id, int stock);
    @Transactional
    @Query("UPDATE Book SET stock = stock - :stock WHERE id = :id") //
    void stockOutById(@NonNull @NotNull Long id, @NotNull int stock); //todo stock can go to negative.
    boolean existsByIdAndStockGreaterThan(Long id, int stock);

}
