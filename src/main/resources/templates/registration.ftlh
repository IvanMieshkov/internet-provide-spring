<#import "fragments/error.ftl" as error/>
<!doctype html>
<html lang="en">
<head>
    <#include "fragments/bootstrap.ftl"/>

    <title>Registration</title>
</head>
<body>

<#include "fragments/nav-bar.ftl" />
<br>
<main class="registration-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><@spring.message "title.registration"/></div>

                    <div class="card-body">
                        <@error.error/>
                        <form action="/registration" method="POST" enctype="multipart/form-data">

                            <div class="form-group row">
                                <label for="nameEn" class="col-md-4 col-form-label text-md-right"><@spring.message "field.user.name.en"/> </label>
                                <div class="col-md-6">
                                    <input type="text"
                                           class="form-control ${(nameEnError??)?string('is-invalid', '')}"
                                           value="<#if userDTO??>${userDTO.nameEn}</#if>" name="nameEn"/>
                                    <#if nameEnError??>
                                        <div class="invalid-feedback">
                                            ${nameEnError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="nameUkr" class="col-md-4 col-form-label text-md-right"><@spring.message "field.user.name.ukr"/></label>
                                <div class="col-md-6">
                                    <input type="text"
                                           class="form-control ${(nameUkrError??)?string('is-invalid', '')}"
                                           value="<#if userDTO??>${userDTO.nameUkr}</#if>" name="nameUkr"/>
                                    <#if nameUkrError??>
                                        <div class="invalid-feedback">
                                            ${nameUkrError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="phone" class="col-md-4 col-form-label text-md-right"><@spring.message "field.user.phone"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="phone"
                                           class="form-control ${(phoneError??)?string('is-invalid', '')}"
                                           name="phone"
                                           value="<#if userDTO??>${userDTO.phone}</#if>"/>
                                    <#if phoneError??>
                                        <div class="invalid-feedback">
                                            ${phoneError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right"><@spring.message "field.user.password"/></label>
                                <div class="col-md-6">
                                    <input type="password" id="password"
                                           class="form-control ${(passwordError??)?string('is-invalid', '')}"
                                           name="password"/>

                                    <#if passwordError??>
                                        <div class="invalid-feedback">
                                            ${passwordError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="email" class="col-md-4 col-form-label text-md-right"><@spring.message "field.user.email"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="email"
                                           class="form-control ${(emailError??)?string('is-invalid', '')}"
                                           name="email"
                                           value="<#if userDTO??>${userDTO.email}</#if>"/>
                                    <#if emailError??>
                                        <div class="invalid-feedback">
                                            ${emailError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="address" class="col-md-4 col-form-label text-md-right"><@spring.message "field.user.address"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="address"
                                           class="form-control ${(addressError??)?string('is-invalid', '')}"
                                           name="address"
                                           value="<#if userDTO??>${userDTO.address}</#if>"/>

                                    <#if addressError??>
                                        <div class="invalid-feedback">
                                            ${addressError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <@spring.message "button.add.user"/>
                                </button>
                                <a href="/" class="btn btn-link">
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
<#include "fragments/footer.ftlh"/>
</body>
</html>