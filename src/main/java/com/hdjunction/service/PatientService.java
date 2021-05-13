package com.hdjunction.service;

import com.hdjunction.common.CodeGroup;
import com.hdjunction.dto.PatientRequest;
import com.hdjunction.dto.PatientResponse;
import com.hdjunction.entity.DateOfBirth;
import com.hdjunction.entity.Hospital;
import com.hdjunction.entity.Patient;
import com.hdjunction.entity.Phone;
import com.hdjunction.exception.NotFoundException;
import com.hdjunction.repository.HospitalRepository;
import com.hdjunction.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PatientService {
    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public PatientResponse enroll(final PatientRequest request) {
        final Hospital hospital = hospitalRepository.findById(request.getHospitalId())
            .orElseThrow(NotFoundException::new);

        final CodeGroup.Code sexCode = CodeGroup.Code.findCodeBy(request.getSex());
        final DateOfBirth dateOfBirth = DateOfBirth.of(request.getDateOfBirth());
        final Phone phoneNo = Phone.of(request.getPhoneNo());

        final Patient savedPatient = patientRepository.save(Patient.of(hospital, request.getName(), sexCode, dateOfBirth, phoneNo));

        return new PatientResponse(savedPatient);
    }

    @Transactional
    public PatientResponse update(final PatientRequest request) {
        final Patient patient = patientRepository.findById(request.getId())
            .orElseThrow(NotFoundException::new);

        final CodeGroup.Code sexCode = CodeGroup.Code.findCodeBy(request.getSex());
        final DateOfBirth dateOfBirth = DateOfBirth.of(request.getDateOfBirth());
        final Phone phoneNo = Phone.of(request.getPhoneNo());

        patient.update(request.getName(), sexCode, dateOfBirth, phoneNo);

        return new PatientResponse(patient);
    }
}
