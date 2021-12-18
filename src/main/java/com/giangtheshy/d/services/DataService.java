package com.giangtheshy.d.services;

import com.giangtheshy.d.models.Coordinate;
import com.giangtheshy.d.models.Element;
import com.giangtheshy.d.models.Enums.Block;
import com.giangtheshy.d.models.Part;
import com.giangtheshy.d.models.Point;
import com.giangtheshy.d.repositories.CoordinateRepository;
import com.giangtheshy.d.repositories.ElementRepository;
import com.giangtheshy.d.repositories.PartRepository;
import com.giangtheshy.d.repositories.PointRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

  @Autowired private CoordinateRepository coordinateRepository;
  @Autowired private ElementRepository elementRepository;
  @Autowired private PartRepository partRepository;
  @Autowired private PointRepository pointRepository;

  @Transactional
  public Element addData(Element element) {
    Element resultElement = new Element();
    List<Coordinate> coordinates = new ArrayList<>();

    Element saveElement =
        elementRepository.save(
            Element.builder()
                .color(element.getColor())
                .block(element.getBlock())
                .size(element.getSize())
                .build());
    BeanUtils.copyProperties(saveElement, resultElement);
    element
        .getCoordinates()
        .forEach(
            coordinate -> {
              Coordinate saveCoordinate =
                  coordinateRepository.save(
                      Coordinate.builder().type(coordinate.getType()).build());
              coordinateRepository.updateId(saveCoordinate.getId(), saveElement.getId());
              List<Part> parts = new ArrayList<>();
              coordinate
                  .getParts()
                  .forEach(
                      part -> {
                        Part savePart = partRepository.save(Part.builder().build());
                        partRepository.updateId(savePart.getId(), saveCoordinate.getId());
                        List<Point> points = new ArrayList<>();
                        part.getPoints()
                            .forEach(
                                point -> {
                                  Point savePoint =
                                      pointRepository.save(
                                          Point.builder()
                                              .x(point.getX())
                                              .y(point.getY())
                                              .z(point.getZ())
                                              .part(savePart)
                                              .build());
                                  pointRepository.updateId(savePoint.getId(), savePart.getId());
                                  points.add(savePoint);
                                });
                        savePart.setPoints(points);
                        parts.add(savePart);
                      });
              saveCoordinate.setParts(parts);
              coordinates.add(saveCoordinate);
            });
    resultElement.setCoordinates(coordinates);
    return resultElement;
  }

  public List<Element> getAllData() {
    return elementRepository.findAll();
  }

  public List<Element> getDataByBlock(Block block) {
      List<Element> elements = elementRepository.findAll();
    return elements.stream()
        .filter(element -> element.getBlock().value.equals(block.value))
        .collect(Collectors.toList());
  }
}
