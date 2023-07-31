package com.lojavirtual.controller;

import com.lojavirtual.dto.PessoaFisicaDTO;
import com.lojavirtual.dto.PessoaJuridicaDTO;
import com.lojavirtual.dto.PessoaResponseDto;
import com.lojavirtual.model.PessoaFisica;
import com.lojavirtual.model.PessoaJuridica;
import com.lojavirtual.service.PessoaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PessoaController {
    private final PessoaUserService pessoaUserService;

    @ResponseBody
    @PostMapping(value = "/cadastrarPessoaJuridica")
    public ResponseEntity<PessoaResponseDto> salvarPessoaJuridica(@RequestBody PessoaJuridicaDTO pessoaJuridicaDTO) throws Exception {
        var pessoaJuridicaRegistrada = pessoaUserService.cadastrarPessoaJuridica(pessoaJuridicaDTO);
        return new ResponseEntity<>(pessoaJuridicaRegistrada, HttpStatus.CREATED);
    }

    @ResponseBody
    @PostMapping(value = "/cadastrarPessoaFisica")
    public ResponseEntity<PessoaResponseDto> salvarPessoaFisica(@RequestBody PessoaFisicaDTO pessoaFisicaDTO) throws Exception {
        var pessoaFisicaRegistrada = pessoaUserService.cadastrarPessoaFisica(pessoaFisicaDTO);
        return new ResponseEntity<>(pessoaFisicaRegistrada, HttpStatus.CREATED);
    }
}
