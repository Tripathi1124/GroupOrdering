package service.Controller;

import com.dao.EventDAO;
import com.dao.GroupDAO;
import com.dao.RegisterDAO;
import com.model.Event;
import com.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * GroupOrderController
 */
@RestController
public class GroupOrderController {

    @Autowired
    private RegisterDAO registerDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private GroupDAO groupDAO;

    @RequestMapping(value = "/createEvent", method = RequestMethod.POST)
    public String createEvent(Event event) {
        try {
            eventDAO.insertEvent(event);
            System.out.print("Event created and Inserted in DB");
        } catch (Exception e) {
            System.out.print("Exception occurred while creating Event");
            e.printStackTrace();
        }

        return "Event Creation successful";
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public String addGroup(Group group) {
        try {
            groupDAO.insertPerson(group);
            System.out.print("Person added in Group");
        } catch (Exception e) {
            System.out.print("Exeption while adding person in Event group");
            e.printStackTrace();
        }

        return "Person added successfully in Event group";
    }

    @RequestMapping(value = "/perPersonCost", method = RequestMethod.POST)
    public Long perPersonCost(Long budget, Long eventId) {
        try {
            List<Group> groupList = groupDAO.getEventGroup(eventId);
            Integer personCount = groupList.size();
            Long perPersonCost = budget/personCount;

            return perPersonCost;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/showEvent", method = RequestMethod.GET)
    public List<Group> showEvent(Long eventId) {
        try {
            List<Group> groupList = groupDAO.getEventGroup(eventId);
            System.out.print("Group fetched!!");
            return groupList;
        } catch (Exception e) {
            System.out.print("Exception occurred while fetching group");
            e.printStackTrace();
        }

        return null;
    }
}
