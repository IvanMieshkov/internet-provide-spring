<#import "../fragments/error.ftl" as error/>
<!doctype html>
<html lang="en">
<head>
    <#include "../fragments/bootstrap.ftl"/>

    <title>Tariff edit</title>
</head>
<body>

<#include "../fragments/nav-bar.ftl" />
<br>
<main class="tariff-edit-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><@spring.message "button.edit.tariff"/></div>

                    <div class="card-body">
<#--                        <@error.error/>-->
                        <form action="/admin/tariff-edit/${tariff.id}" method="POST"
<#--                              enctype="multipart/form-data"-->
                        >

                            <div class="form-group row">
                                <input type="text" id="nameEn" class="form-control" name="nameEn" placeholder="${tariff.nameEn}">
                            </div>

                            <div class="form-group row">
                                <input type="text" id="nameUkr" class="form-control" name="nameUkr" placeholder="${tariff.nameUkr}">
                            </div>

                            <div class="form-group row">
                                <input type="number" id="price" class="form-control" name="price" placeholder="${tariff.price}">
                            </div>




<#--                            <div class="form-group row">-->
<#--                                <label for="nameUkr" class="col-md-4 col-form-label text-md-right"><@spring.message "title.tariff.name.ukr"/></label>-->
<#--                                <div class="col-md-6">-->
<#--                                    <input type="text"-->
<#--                                           class="form-control ${(nameUkrError??)?string('is-invalid', '')}"-->
<#--                                           value="<#if tariffDTO??>${tariffDTO.nameUkr}</#if>" name="nameUkr"/>-->
<#--                                    <#if nameUkrError??>-->
<#--                                        <div class="invalid-feedback">-->
<#--                                            ${nameUkrError}-->
<#--                                        </div>-->
<#--                                    </#if>-->
<#--                                </div>-->
<#--                            </div>-->

<#--                            <div class="form-group row">-->
<#--                                <label for="phone" class="col-md-4 col-form-label text-md-right"><@spring.message "title.tariff.price"/> </label>-->
<#--                                <div class="col-md-6">-->
<#--                                    <input type="text" id="price"-->
<#--                                           class="form-control ${(priceError??)?string('is-invalid', '')}"-->
<#--                                           name="price"-->
<#--                                           value="<#if tariffDTO??>${tariffDTO.price}</#if>"/>-->
<#--                                    <#if priceError??>-->
<#--                                        <div class="invalid-feedback">-->
<#--                                            ${priceError}-->
<#--                                        </div>-->
<#--                                    </#if>-->
<#--                                </div>-->
<#--                            </div>-->

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <@spring.message "button.save"/>
                                </button>
                                <a href="/tariff-list/${tariff.service}" class="btn btn-link">
                                    <@spring.message "button.cancel"/>
                                </a>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>
<br>
<br>
<#include "../fragments/footer.ftlh"/>
</body>
</html>