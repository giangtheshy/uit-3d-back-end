package com.giangtheshy.d.controllers;

import com.giangtheshy.d.models.Element;
import com.giangtheshy.d.models.Enums.Block;
import com.giangtheshy.d.models.Part;
import com.giangtheshy.d.models.Point;
import com.giangtheshy.d.services.DataService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class DataController {

  @Autowired private  DataService dataService;

  @PostMapping("/")
  public ResponseEntity<Element> addData(@RequestBody Element element) {

    return ResponseEntity.ok(dataService.addData(element));
  }

  @GetMapping("/")
  public ResponseEntity<List<Element>> getAllData() {
    return ResponseEntity.ok(dataService.getAllData());
  }
  @GetMapping("/{block}")
  public ResponseEntity<List<Element>> getDataByBlock(@PathVariable Block block){
    return ResponseEntity.ok(dataService.getDataByBlock(block));
  }
}
