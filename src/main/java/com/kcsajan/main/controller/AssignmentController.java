package com.kcsajan.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcsajan.main.model.Assignment;
import com.kcsajan.main.repository.AssignmentRepository;

@CrossOrigin(origins = "http://localhost:3000") // To connect with react application( front-end )
@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

	@Autowired
	private AssignmentRepository assignmentRepository;

	@GetMapping()
	public ResponseEntity<List<Assignment>> getAssignments() {
		try {
			List<Assignment> assignments = new ArrayList<Assignment>();
			assignments.addAll(assignmentRepository.findAll());
			if (assignments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(assignments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Assignment> getAssignment(@PathVariable("id") String id) {
		Optional<Assignment> assignmentData = assignmentRepository.findById(id);
		if (assignmentData.isPresent()) {
			return new ResponseEntity<>(assignmentData.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping()
	public ResponseEntity<Assignment> addAssignment(@RequestBody Assignment assignment) {
		try {
			Assignment _assignment = assignmentRepository.save(assignment);
			return new ResponseEntity<>(_assignment, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Assignment> updateAssignment(@PathVariable("id") String id,
			@RequestBody Assignment assignment) {
		Optional<Assignment> assignmentData = assignmentRepository.findById(id);

		if (assignmentData.isPresent()) {
			Assignment _assignment = assignmentData.get();
			_assignment.setTitle(assignment.getTitle());
			_assignment.setDescription(assignment.getDescription());
			_assignment.setSubmissionDate(assignment.getSubmissionDate());
			_assignment.setStatus(assignment.getStatus());
			return new ResponseEntity<>(assignmentRepository.save(_assignment), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteAssignment(@PathVariable("id") String id) {
		try {
			assignmentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping()
	public ResponseEntity<HttpStatus> deleteAssignments() {
		try {
			assignmentRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
