package com.example.hrworker.resources;

import com.example.hrworker.entities.Worker;
import com.example.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workers")
@RefreshScope
public class WorkerRessource {

    private static Logger logger = LoggerFactory.getLogger(WorkerRessource.class);

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private Environment env;


    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> workers = this.workerRepository.findAll();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findId(@PathVariable long id) {
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("PORT= " + env.getProperty("local.server.port"));

        Worker worker = this.workerRepository.findById(id).get();
        return ResponseEntity.ok(worker);
    }
}
