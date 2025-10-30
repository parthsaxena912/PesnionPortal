package com.drdo.pensionPortal.service;



import com.drdo.pensionPortal.entity.Pensioner;
import com.drdo.pensionPortal.repository.PensionerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PensionerServiceImpl {

    @Autowired
    private PensionerRepository pensionerRepository;

    public List<Pensioner> getAllPensioners() {
        return pensionerRepository.findAll();
    }

    public Pensioner getPensionerById(Long id) {
        Optional<Pensioner> pensioner = pensionerRepository.findById(id);
        return pensioner.orElse(null);
    }

    public Pensioner addPensioner(Pensioner pensioner) {
        return pensionerRepository.save(pensioner);
    }

    public Pensioner updatePensioner(Long id, Pensioner updatedPensioner) {
        Pensioner pensioner = pensionerRepository.findById(id).orElse(null);
        if (pensioner != null) {
            pensioner.setName(updatedPensioner.getName());
            pensioner.setAadharNumber(updatedPensioner.getAadharNumber());
            pensioner.setBankName(updatedPensioner.getBankName());
            pensioner.setAccountNumber(updatedPensioner.getAccountNumber());
            pensioner.setPensionAmount(updatedPensioner.getPensionAmount());
            return pensionerRepository.save(pensioner);
        }
        return null;
    }

    public void deletePensioner(Long id) {
        pensionerRepository.deleteById(id);
    }
}
