package com.hdjunction.api;

import com.hdjunction.dto.CommonResponse;
import com.hdjunction.dto.PatientRequest;
import com.hdjunction.dto.PatientResponse;
import com.hdjunction.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
