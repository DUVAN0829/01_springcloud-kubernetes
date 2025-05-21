package co.duvan.sprinngcloud.msvc.users.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cursos", url = "localhost:8002")
public interface CursoClienteFeign {

    @DeleteMapping("delete-curso-user/{id}")
    void deleteCursoUser(@PathVariable Long id);

}
