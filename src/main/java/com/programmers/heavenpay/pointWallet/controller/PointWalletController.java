package com.programmers.heavenpay.pointWallet.controller;

import com.programmers.heavenpay.common.converter.ResponseConverter;
import com.programmers.heavenpay.common.dto.LinkType;
import com.programmers.heavenpay.common.dto.ResponseDto;
import com.programmers.heavenpay.common.dto.ResponseMessage;
import com.programmers.heavenpay.member.controller.MemberController;
import com.programmers.heavenpay.pointWallet.dto.request.PointWalletCreateRequest;
import com.programmers.heavenpay.pointWallet.dto.request.PointWalletDeleteRequest;
import com.programmers.heavenpay.pointWallet.dto.request.PointWalletGetRequest;
import com.programmers.heavenpay.pointWallet.dto.request.PointWalletUpdateRequest;
import com.programmers.heavenpay.pointWallet.dto.response.PointWalletCreateResponse;
import com.programmers.heavenpay.pointWallet.dto.response.PointWalletDeleteResponse;
import com.programmers.heavenpay.pointWallet.dto.response.PointWalletGetOneResponse;
import com.programmers.heavenpay.pointWallet.dto.response.PointWalletUpdateResponse;
import com.programmers.heavenpay.pointWallet.service.PointWalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Api(tags = "PointWallet")
@RestController
@RequestMapping(value = "/api/v1/point_wallets", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class PointWalletController {
    private final PointWalletService pointWalletService;
    private final ResponseConverter responseConverter;

    private WebMvcLinkBuilder getLinkToAddress() {
        return linkTo(PointWalletController.class);
    }

    @ApiOperation("????????? ?????? ?????? ??????, ????????? ????????? ?????? ID ??????")
    @PostMapping(consumes = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<ResponseDto> addWallet(@Valid @RequestBody PointWalletCreateRequest request) {
        PointWalletCreateResponse response = pointWalletService.create(
                request.getMemberId(),
                request.getAccountId(),
                request.getWalletPoint()
        );

        EntityModel<PointWalletCreateResponse> entityModel = EntityModel.of(
                response,
                getLinkToAddress().withSelfRel().withTitle(HttpMethod.POST.name()),
                getLinkToAddress().slash(response.getId()).withRel(LinkType.READ_METHOD).withType(HttpMethod.GET.name()),
                getLinkToAddress().withRel(LinkType.READ_ALL_METHOD).withType(HttpMethod.GET.name()),
                getLinkToAddress().slash(response.getId()).withRel(LinkType.UPDATE_METHOD).withType(HttpMethod.PATCH.name()),
                getLinkToAddress().slash(response.getId()).withRel(LinkType.DELETE_METHOD).withType(HttpMethod.DELETE.name())
        );

        return responseConverter.toResponseEntity(
                ResponseMessage.POINT_WALLET_INSERT_SUCCESS,
                entityModel
        );
    }

    @ApiOperation("????????? ?????? ?????? ??????, ????????? ????????? ?????? ?????? ??????")
    @PutMapping(value = "/{pointWalletId}", consumes = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<ResponseDto> changePoint(@PathVariable Long pointWalletId, @Valid @RequestBody PointWalletUpdateRequest request) {
        PointWalletUpdateResponse response = pointWalletService.update(
                pointWalletId,
                request.getMemberId(),
                request.getAccountId(),
                request.getWalletPoint()
        );

        EntityModel<PointWalletUpdateResponse> entityModel = EntityModel.of(
                response,
                getLinkToAddress().withRel(LinkType.CREATE_METHOD).withType(HttpMethod.POST.name()),
                getLinkToAddress().slash(response.getId()).withRel(LinkType.READ_METHOD).withType(HttpMethod.GET.name()),
                getLinkToAddress().withRel(LinkType.READ_ALL_METHOD).withType(HttpMethod.GET.name()),
                getLinkToAddress().slash(response.getId()).withSelfRel().withType(HttpMethod.PATCH.name()),
                getLinkToAddress().slash(response.getId()).withRel(LinkType.DELETE_METHOD).withType(HttpMethod.DELETE.name())
        );

        return responseConverter.toResponseEntity(
                ResponseMessage.POINT_WALLET_UPDATE_SUCCESS,
                entityModel
        );
    }

    @ApiOperation("????????? ?????? ?????? ??????, ????????? ????????? ?????? ????????? ??????")
    @DeleteMapping(value = "/{pointWalletId}")
    public ResponseEntity<ResponseDto> deleteWallet(@Valid @PathVariable Long pointWalletId, @Valid @RequestBody PointWalletDeleteRequest request) {
        PointWalletDeleteResponse response = pointWalletService.delete(pointWalletId,
                request.getMemberId(),
                request.getAccountId());

        EntityModel<PointWalletDeleteResponse> entityModel = EntityModel.of(
                response,
                getLinkToAddress().withRel(LinkType.CREATE_METHOD).withType(HttpMethod.POST.name()),
                getLinkToAddress().withRel(LinkType.READ_ALL_METHOD).withType(HttpMethod.GET.name()),
                getLinkToAddress().slash(response.getId()).withSelfRel().withType(HttpMethod.DELETE.name())
        );

        return responseConverter.toResponseEntity(
                ResponseMessage.POINT_WALLET_DELETE_SUCCESS,
                entityModel
        );
    }

    @ApiOperation("????????? ?????? ?????? ??????, ????????? ?????? ?????? ??????")
    @GetMapping(value = "/{pointWalletId}")
    public ResponseEntity<ResponseDto> findOne(@PathVariable Long pointWalletId, @Valid @RequestBody PointWalletGetRequest request) {
        PointWalletGetOneResponse response = pointWalletService.getOne(pointWalletId,
                request.getMemberId(),
                request.getAccountId());

        EntityModel<PointWalletGetOneResponse> entityModel = EntityModel.of(
                response,
                getLinkToAddress().withRel(LinkType.CREATE_METHOD).withType(HttpMethod.POST.name()),
                getLinkToAddress().slash(response.getId()).withSelfRel().withType(HttpMethod.GET.name()),
                getLinkToAddress().withRel(LinkType.READ_ALL_METHOD).withType(HttpMethod.GET.name()),
                getLinkToAddress().slash(response.getId()).withRel(LinkType.UPDATE_METHOD).withType(HttpMethod.PATCH.name()),
                getLinkToAddress().slash(response.getId()).withRel(LinkType.DELETE_METHOD).withType(HttpMethod.DELETE.name())
        );

        return responseConverter.toResponseEntity(
                ResponseMessage.POINT_WALLET_SEARCH_SUCCESS,
                entityModel
        );
    }

    @ApiOperation("?????? ????????? ?????? ??????, ????????? ?????? Page ??????")
    @GetMapping()
    public ResponseEntity<ResponseDto> findAll(Pageable pageable) {
        Page<PointWalletGetOneResponse> responses = pointWalletService.getAll(pageable);

        Link link = getLinkToAddress().withSelfRel().withType(HttpMethod.GET.name());

        return responseConverter.toResponseEntity(
                ResponseMessage.POINT_WALLET_SEARCH_SUCCESS,
                responses,
                link
        );
    }
}
