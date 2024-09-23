package com.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ResponseTransformer;
import java.util.List;
import static spark.Spark.*;

public class AlunoController {
  private final AlunoService alunoService;
  private final ObjectMapper objectMapper;
    public AlunoController() {
      this.alunoService = new AlunoService();
      this.objectMapper = new ObjectMapper();
  }
  public void init() {
   port(4567);
   // Endpoint GET para retornar todos os alunos na URI "/alunos".
   get("/alunos", "application/json", (req, res) -> {
    List<Aluno> alunos = alunoService.getAllAlunos();
      res.status(200);
        return alunos;
     }, json());
    }
  private ResponseTransformer json() {
    return object -> {
      try {
    return objectMapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException("Erro ao converter objeto para JSON", e);
     }
   };
  }
   public static void main(String[] args) {
    new AlunoController().init();
 }
}
