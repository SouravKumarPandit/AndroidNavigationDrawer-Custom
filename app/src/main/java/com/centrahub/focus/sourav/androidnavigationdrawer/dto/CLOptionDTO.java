/*
 * Copyright 2018 Sourav Kumar Pandit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.centrahub.focus.sourav.androidnavigationdrawer.dto;

/**
 * Created by sourav on 05-Feb-18.
 */

public class CLOptionDTO {
    int optionImage;

    int optionId;

    String optionLable;

    boolean isSelected;

/*    public CLOptionDTO(String optionLable, int optionId) {
        this.optionLable = optionLable;
        this.optionId = optionId;
    }*/

    public CLOptionDTO(String optionLable, int optionId, int optionImage) {
        this.optionLable = optionLable;
        this.optionId = optionId;
        this.optionImage=optionImage;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getOptionImage() {
        return optionImage;
    }

    public void setOptionImage(int optionImage) {
        this.optionImage = optionImage;
    }

    public String getOptionLable() {
        return optionLable;
    }

    public void setOptionLable(String optionLable) {
        this.optionLable = optionLable;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        CLOptionDTO itemCompare = (CLOptionDTO) obj;
        if(itemCompare.getOptionLable().equals(this.getOptionLable()))
            return true;

        return false;
    }

}
