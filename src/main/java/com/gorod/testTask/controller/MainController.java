package com.gorod.testTask.controller;

import com.gorod.testTask.dto.AbonentDTO;
import com.gorod.testTask.dto.ServiceDTO;
import com.gorod.testTask.entity.Abonent;
import com.gorod.testTask.service.AbonentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class MainController {
    @Autowired
    private AbonentService abonentService;

    @Autowired
    private com.gorod.testTask.service.Service service;

    public static final String GET_ALL_ABONENTS = "abonents";
    public static final String GET_ABONENTS_BY_ID_SERVICE = "abonents/{idService}";
    public static final String CREATE_ABONENT = "abonent";
    public static final String GET_HIERARCHY = "hierarchy";
    public static final String DELETE_SERVICE = "delete/{id}";

    /**
     * Получение всех абонентов с возможностью сортировки и фильтру по полю "account"
     * @param sorted - необязательный, по дефолту "false",
     *               при установке в "true" абоненты сортируются по полю "account";
     * @param filter - необязательный, по дефолту пустой
     *               при заполнении фильтра абоненты фильтруются по полю "account";
     * @param pageable
     * @return возвращает список абонентов
     */
    @GetMapping(GET_ALL_ABONENTS)
    public ResponseEntity<List<AbonentDTO>> getAbonents(
            @RequestParam(required = false, defaultValue = "false") boolean sorted,
            @RequestParam(required = false, defaultValue = "") String filter,
            Pageable pageable
    ) {
        List<AbonentDTO> abonents = abonentService.getAbonents(sorted, filter, pageable);
        return ResponseEntity.ok().body(abonents);
    }

    /**
     * Получение абонентов с определённой услугой
     * @param idService - обязательный, id услуги, по которому ведётся поиск абонентов;
     * @param children - необязательный, по дефолту "false",
     *                 при установке в "false" у абонентов не показываются дочернии услуги,
     *                 при установке в "true" у абонентов показываются дочернии услуги;
     * @param pageable
     * @return возвращает список абонентов с/без дочерними услугами
     */
    @GetMapping(GET_ABONENTS_BY_ID_SERVICE)
    public ResponseEntity<List<AbonentDTO>> getAbonentsByIdService(
            @PathVariable(name = "idService") Long idService,
            @RequestParam(name = "children", required = false, defaultValue = "false") boolean children,
            Pageable pageable
    ) {
        List<AbonentDTO> abonents = abonentService.getAbonentsByIdService(idService, children, pageable);
        return ResponseEntity.ok().body(abonents);
    }


    /**
     * Создание абонента
     * @param fio - обязательный, по дефолту "ФИО", указывается ФИО создаваемого абонента;
     * @param account - обязательный, указывается целочисленный номер абонента(лицевой счет);
     * @param idService - обязательный, по дефолту 1, указывается id оказываемой услуги;
     * @return возвращает id, при удачном сохранении,
     *          -1, при неудачном сохранении
     */
    @PostMapping(CREATE_ABONENT)
    public ResponseEntity<Long> createAbonent(
            @RequestParam(required = true, defaultValue = "ФИО", name = "fio") String fio,
            @RequestParam(required = true, name = "account") Long account,
            @RequestParam(required = true, defaultValue = "1", name = "id_service") Long idService
    ) {
        Abonent abonent = abonentService.createAbonent(fio, account, idService);
        if (abonent != null) {
            return ResponseEntity.ok().body(abonent.getId());
        } else {
            return ResponseEntity.badRequest().body(-1L);
        }
    }

    /**
     * Получение иерархии услуг
     * @return возвращает иерархию услуг
     */
    @GetMapping(GET_HIERARCHY)
    public ResponseEntity<ServiceDTO> getHierarchy() {
        ServiceDTO hierarhy = service.getHierarhy();
        return ResponseEntity.ok().body(hierarhy);
    }


    /**
     * Удалениеи услуги по id
     * @param id - обязательный, id услуги по которому производится удаление
     * @param force - необязательный, по дефолту "false",
     *              при установке в "false" удаление происходит, если на услугу никто не ссылается,
     *              при установке в "true" удаление происходит каскадно
     * @return возвращает "Удалено" при успешном удалении
     */
    @DeleteMapping(DELETE_SERVICE)
    public ResponseEntity<String> deleteService(
            @PathVariable(name = "id") Long id,
            @RequestParam(required = false, defaultValue = "false", name = "force") boolean force
    ) {
        return service.deleteService(id, force);
    }
}
