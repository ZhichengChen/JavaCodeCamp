/*
 * Copyright 2017-2021 Dromara.org
 *
 * Licen0ed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dromara.hmily.demo.dubbo.order.common.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The enum Order status enum.
 *
 * @author xiaoyu
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    /**
     * Not pay order status enum.
     */
    NOT_PAY,

    /**
     * Paying order status enum.
     */
    PAYING,

    /**
     * Pay fail order status enum.
     */
    PAY_FAIL,

    /**
     * Pay success order status enum.
     */
    PAY_SUCCESS;

}