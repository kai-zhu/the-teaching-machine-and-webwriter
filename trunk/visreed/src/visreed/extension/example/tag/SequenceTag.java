/**
 * SequenceTag.java
 * 
 * @date: Nov 30, 2011
 * @author: Xiaoyu Guo
 * This file is part of the Teaching Machine project.
 */
package visreed.extension.example.tag;

import java.util.ArrayList;
import java.util.List;

import visreed.model.VisreedPayload;
import visreed.model.VisreedTag;
import visreed.model.payload.SequencePayload;

/**
 * @author Xiaoyu Guo
 *
 */
public class SequenceTag extends ExampleTag {
    protected SequenceTag(){
        super(TagCategory.SEQ);
    }
    
    @Override
    public String toString(){
        return "SEQUENCE";
    }
    
    @Override
    public boolean canHoldExactOneChild(){
    	return false;
    }

    @Override
    public VisreedPayload defaultPayload() {
        return new SequencePayload(this);
    }

    @Override
    public List<VisreedTag> defaultTagSequence() {
        return new ArrayList<VisreedTag>(0);
    }

    @Override
    public String getDescription() {
        return "SEQ";
    }
    
    private static final SequenceTag instance = new SequenceTag();

    protected static SequenceTag getInstance() {
        return instance;
    };
}
