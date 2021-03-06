/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.cmmn.converter.export;

import java.util.List;

import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.lang3.StringUtils;
import org.flowable.cmmn.model.HumanTask;

public class HumanTaskExport extends AbstractPlanItemDefinitionExport {
    
    public static void writeHumanTask(HumanTask humanTask, XMLStreamWriter xtw) throws Exception {
        // start human task element
        xtw.writeStartElement(ELEMENT_HUMAN_TASK);
        writeCommonPlanItemDefinitionAttributes(humanTask, xtw);
        writeBlockingAttribute(xtw, humanTask);
        
        if (StringUtils.isNotEmpty(humanTask.getAssignee())) {
            xtw.writeAttribute(FLOWABLE_EXTENSIONS_PREFIX, FLOWABLE_EXTENSIONS_NAMESPACE, ATTRIBUTE_ASSIGNEE, humanTask.getAssignee());
        }
        
        if (StringUtils.isNotEmpty(humanTask.getOwner())) {
            xtw.writeAttribute(FLOWABLE_EXTENSIONS_PREFIX, FLOWABLE_EXTENSIONS_NAMESPACE, ATTRIBUTE_OWNER, humanTask.getOwner());
        }
        
        if (humanTask.getCandidateUsers() != null && humanTask.getCandidateUsers().size() > 0) {
            xtw.writeAttribute(FLOWABLE_EXTENSIONS_PREFIX, FLOWABLE_EXTENSIONS_NAMESPACE, ATTRIBUTE_CANDIDATE_USERS, 
                            convertListToCommaSeparatedString(humanTask.getCandidateUsers()));
        }
        
        if (humanTask.getCandidateGroups() != null && humanTask.getCandidateGroups().size() > 0) {
            xtw.writeAttribute(FLOWABLE_EXTENSIONS_PREFIX, FLOWABLE_EXTENSIONS_NAMESPACE, ATTRIBUTE_CANDIDATE_GROUPS, 
                            convertListToCommaSeparatedString(humanTask.getCandidateGroups()));
        }
        
        if (StringUtils.isNotEmpty(humanTask.getFormKey())) {
            xtw.writeAttribute(FLOWABLE_EXTENSIONS_PREFIX, FLOWABLE_EXTENSIONS_NAMESPACE, ATTRIBUTE_FORM_KEY, humanTask.getFormKey());
        }
        
        // end human task element
        xtw.writeEndElement();
    }
    
    protected static String convertListToCommaSeparatedString(List<String> values) {
        StringBuilder valueBuilder = new StringBuilder();
        for (String value : values) {
            if (valueBuilder.length() > 0) {
                valueBuilder.append(",");
            }
            
            valueBuilder.append(value);
        }
        
        return valueBuilder.toString();
    }
}
