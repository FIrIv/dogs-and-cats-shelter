package com.telegrambot.jd501.service.CatService;

import com.telegrambot.jd501.Exceptions.PetReportNotFoundException;
import com.telegrambot.jd501.model.cat.CatReport;
import com.telegrambot.jd501.model.cat.CatUser;
import com.telegrambot.jd501.repository.Cat.CatReportRepository;
import com.telegrambot.jd501.repository.Cat.CatUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.telegrambot.jd501.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatReportServiceTest {
    @Mock
    private CatReportRepository catReportRepository;
    @Mock
    private CatUserRepository catUserRepository;

    @InjectMocks
    private CatReportService out;

    @Test
    void getAllCatReport() {
        when(catReportRepository.findAll()).thenReturn(CAT_REPORT_LIST);

        assertEquals(out.getAllCatReport(), CAT_REPORT_LIST);
    }

    @Test
    void createCatReport() {
        when(catReportRepository.save(any(CatReport.class))).thenReturn(CAT_REPORT_1);

        assertEquals(out.createCatReport(CAT_REPORT_1), CAT_REPORT_1);
    }

    @Test
    void updateCatReport() {
        when(catReportRepository.findById(anyLong())).thenReturn(Optional.of(CAT_REPORT_1));
        when(catReportRepository.save(any(CatReport.class))).thenReturn(CAT_REPORT_11);

        assertEquals(out.updateCatReport(CAT_REPORT_11), CAT_REPORT_11);
    }

    @Test
    void deleteCatReport() {
        when(catReportRepository.findById(anyLong())).thenReturn(Optional.of(CAT_REPORT_1));

        assertEquals(out.deleteCatReport(ID1), CAT_REPORT_1);
    }

    @Test
    void getAllCatReportByCatUser() {
        when(catReportRepository.findCatReportsByCatUserOrderByDateOfReport(any(CatUser.class))).thenReturn(List.of(CAT_REPORT_1));

        assertEquals(out.getAllCatReportByCatUser(CAT_USER_3), CAT_REPORT_LIST_BY_USER);
    }

    @Test
    void getCatReportByCatUserAndDateOfReport() {
        when(catReportRepository.findCatReportByCatUserAndDateOfReport(any(CatUser.class), any(LocalDate.class))).thenReturn(CAT_REPORT_1);

        assertEquals(out.getCatReportByCatUserAndDateOfReport(CAT_USER_1,DATE_OF_REPORT_1),CAT_REPORT_1);
    }

    @Test
    void getAllReportsByChatId() {
        when(catUserRepository.findCatUserByChatId(anyLong())).thenReturn(CAT_USER_1);
        when(catReportRepository.findAllByCatUser(any(CatUser.class))).thenReturn(List.of(CAT_REPORT_1));

        assertEquals(out.getAllReportsByChatId(CHAT_ID_1), CAT_REPORT_LIST_BY_USER);
    }
    @Test
    void petReportNotFoundExceptionTest1() {
        when(catReportRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PetReportNotFoundException.class,() ->out.deleteCatReport(ID2));
    }
    @Test
    void petReportNotFoundExceptionTest2() {
        when(catReportRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PetReportNotFoundException.class,() ->out.updateCatReport(CAT_REPORT_2));
    }
}