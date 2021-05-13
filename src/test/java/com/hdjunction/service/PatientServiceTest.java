package com.hdjunction.service;

import com.hdjunction.common.CodeGroup;
import com.hdjunction.dto.PatientRequest;
import com.hdjunction.dto.PatientResponse;
import com.hdjunction.entity.*;
import com.hdjunction.exception.NotFoundException;
import com.hdjunction.repository.HospitalRepository;
import com.hdjunction.repository.PatientRepository;
import com.hdjunction.repository.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("환자 CRUD 테스트")
@SpringBootTest
@Transactional
class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @DisplayName("환자 등록 테스트")
    @ParameterizedTest
    @MethodSource("enrollList")
    void savePatientTest(final String name, final String sexCode, final String dateOfBirth, final String phoneNo) throws Exception {
        // Arrange
        final Hospital hospital = new Hospital("에이치디정션", UUID.randomUUID().toString(), "김정션");
        hospitalRepository.save(hospital);

        final PatientRequest request = new PatientRequest(null, Long.toString(hospital.getId()), name, sexCode, dateOfBirth, phoneNo);

        // Act
        final PatientResponse response = patientService.enroll(request);

        // Assert
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getDateOfBirth()).isEqualTo(dateOfBirth);
        assertThat(response.getSex()).isEqualTo(sexCode);
        assertThat(response.getPhoneNo()).isEqualTo(phoneNo);
    }

    private static Stream<Arguments> enrollList() {
        return Stream.of(
            Arguments.of("김정션", "F", "19970513", "01012345678"),
            Arguments.of("홍길동", "M", "20071213", "01091720482"),
            Arguments.of("베지밀", "F", "19921211", "01072819621"),
            Arguments.of("맛있음", "M", "19990722", "0101881677")
        );
    }

    @DisplayName("환자 정보 수정 테스트")
    @ParameterizedTest
    @MethodSource("enrollList")
    void updatePatientTest(final String name, final String sexCode, final String dateOfBirth, final String phoneNo) throws Exception {
        // Arrange
        final Hospital hospital = new Hospital("에이치디정션", UUID.randomUUID().toString(), "김정션");
        hospitalRepository.save(hospital);

        final PatientRequest request = new PatientRequest(null, Long.toString(hospital.getId()), "테스트1", "F", "20000303", "01038281921");

        // Act
        final PatientResponse originPatient = patientService.enroll(request);

        final PatientResponse updateResponse = patientService.update(
            new PatientRequest(originPatient.getId(), Long.toString(hospital.getId()), name, sexCode, dateOfBirth, phoneNo));

        // Assert
        assertThat(updateResponse.getName()).isEqualTo(name);
        assertThat(updateResponse.getDateOfBirth()).isEqualTo(dateOfBirth);
        assertThat(updateResponse.getSex()).isEqualTo(sexCode);
        assertThat(updateResponse.getPhoneNo()).isEqualTo(phoneNo);
    }

    @DisplayName("환자 정보 조회 테스트")
    @Test
    void findPatientTest() throws Exception {
        // Arrange
        final Hospital hospital = new Hospital("에이치디정션", UUID.randomUUID().toString(), "김정션");
        hospitalRepository.save(hospital);

        final PatientRequest request = new PatientRequest(null, Long.toString(hospital.getId()), "홍길동", "M", "20000303", "01038281921");
        final Patient findPatient = patientRepository.findById(Long.parseLong(patientService.enroll(request).getId()))
            .orElseThrow(NotFoundException::new);

        final Visit visit = new Visit(hospital, findPatient, CodeGroup.Code.VISITING);
        findPatient.visitHospital(visit);
        visitRepository.save(visit);

        // Act
        final PatientResponse response = patientService.findPatient(findPatient.getId());

        // Assert
        assertThat(response.getName()).isEqualTo("홍길동");
        assertThat(response.getDateOfBirth()).isEqualTo("20000303");
        assertThat(response.getSex()).isEqualTo("M");
        assertThat(response.getPhoneNo()).isEqualTo("01038281921");
        assertThat(response.getRecentVisit()).isNotEmpty();
    }

    @DisplayName("환자 정보 삭제 테스트")
    @Test
    void deletePatientTest() throws Exception {
        // Arrange
        final Hospital hospital = new Hospital("에이치디정션", UUID.randomUUID().toString(), "김정션");
        hospitalRepository.save(hospital);

        final PatientRequest request = new PatientRequest(null, Long.toString(hospital.getId()), "홍길동", "M", "20000303", "01038281921");
        final Patient findPatient = patientRepository.findById(Long.parseLong(patientService.enroll(request).getId()))
            .orElseThrow(NotFoundException::new);

        // Act
        patientService.remove(findPatient.getId());

        // Assert
        assertThrows(NotFoundException.class, () -> patientRepository.findById(findPatient.getId()).orElseThrow(NotFoundException::new));
    }

    @DisplayName("환자 목록 조회 테스트")
    @Test
    void findPatientsTest() throws Exception {
        // Arrange
        final Hospital hospital = new Hospital("에이치디정션", UUID.randomUUID().toString(), "김정션");
        hospitalRepository.save(hospital);

        final int patientsSize = 10;

        for (int i = 0; i < patientsSize; i++) {
            final Patient patient = Patient.of(hospital, "name " + i, CodeGroup.Code.MALE, DateOfBirth.of("20130712"), Phone.of("01038281921"));
            patientRepository.save(patient);

            final Visit visit = new Visit(hospital, patient, CodeGroup.Code.VISITING);
            patient.visitHospital(visit);
        }

        // Act
        final List<PatientResponse> patients = patientService.findPatients();

        // Assert
        assertThat(patients.size()).isEqualTo(patientsSize);
    }
}
