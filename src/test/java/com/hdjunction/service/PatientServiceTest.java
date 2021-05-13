package com.hdjunction.service;

import com.hdjunction.dto.PatientRequest;
import com.hdjunction.dto.PatientResponse;
import com.hdjunction.entity.Hospital;
import com.hdjunction.repository.HospitalRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("환자 CRUD 테스트")
@SpringBootTest
@Transactional
class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Autowired
    private HospitalRepository hospitalRepository;

    @DisplayName("환자 등록 테스트")
    @ParameterizedTest
    @MethodSource("enrollList")
    void savePatientTest(final String name, final String sexCode, final String dateOfBirth, final String phoneNo) throws Exception {
        // Arrange
        final Hospital hospital = new Hospital("에이치디정션", UUID.randomUUID().toString(), "김정션");
        hospitalRepository.save(hospital);

        final PatientRequest request = new PatientRequest(null, hospital.getId(), name, sexCode, dateOfBirth, phoneNo);

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

        final PatientRequest request = new PatientRequest(null, hospital.getId(), "테스트1", "F", "20000303", "01038281921");

        // Act
        final PatientResponse originPatient = patientService.enroll(request);

        final PatientResponse updateResponse = patientService.update(
            new PatientRequest(Long.parseLong(originPatient.getId()), hospital.getId(), name, sexCode, dateOfBirth, phoneNo));

        // Assert
        assertThat(updateResponse.getName()).isEqualTo(name);
        assertThat(updateResponse.getDateOfBirth()).isEqualTo(dateOfBirth);
        assertThat(updateResponse.getSex()).isEqualTo(sexCode);
        assertThat(updateResponse.getPhoneNo()).isEqualTo(phoneNo);
    }
}
