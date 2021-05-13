package com.hdjunction.api;

import com.hdjunction.dto.CommonResponse;
import com.hdjunction.dto.PatientRequest;
import com.hdjunction.dto.PatientResponse;
import com.hdjunction.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<CommonResponse<PatientResponse>> enroll(@RequestBody final PatientRequest request) {
        final PatientResponse patient = patientService.enroll(request);

        return ResponseEntity.created(URI.create("/patients/" + patient.getId()))
            .body(new CommonResponse<>(patient));
    }

    @PutMapping
    public ResponseEntity<CommonResponse<PatientResponse>> update(@RequestBody final PatientRequest request) {
        patientService.update(request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<PatientResponse>> remove(@PathVariable final Long id) {
        patientService.remove(id);

        return ResponseEntity.noContent().build();
    }
}
