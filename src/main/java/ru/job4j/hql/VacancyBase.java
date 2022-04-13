package ru.job4j.hql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vacancy_base")
public class VacancyBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "filter_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "vacancy_in_vacancy_base",
            joinColumns = @JoinColumn(name = "vacancy_base_id"),
            inverseJoinColumns = @JoinColumn(name = "vacancy_id"))
    private List<Vacancy> vacancyList = new ArrayList<>();

    public static VacancyBase of(String name) {
        VacancyBase vacancyBase = new VacancyBase();
        vacancyBase.name = name;
        return vacancyBase;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }

    public void addVacancy(Vacancy vacancy) {
        this.vacancyList.add(vacancy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VacancyBase that = (VacancyBase) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("VacancyBase: id=%s, name=%s, vacancyList=%s", id, name, vacancyList);
    }
}
