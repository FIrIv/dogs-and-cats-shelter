package com.telegrambot.jd501.repository.Cat;

import com.telegrambot.jd501.model.cat.Cat;
import com.telegrambot.jd501.model.cat.CatReport;
import com.telegrambot.jd501.model.cat.CatUser;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;

public interface CatReportRepository extends JpaRepository<CatReport, Long> {

    List<CatReport> findCatReportsByCatUserOrderByDateOfReport(CatUser user);

    CatReport findCatReportByCatUserAndDateOfReport (CatUser user, LocalDate dateOfReport);

    List<CatReport> findAllByCatUser (CatUser catUser);
}