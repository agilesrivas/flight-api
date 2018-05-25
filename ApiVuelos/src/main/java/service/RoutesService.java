package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RepositoryCabin;

@Service
public class RoutesService {

    @Autowired
    private RepositoryRoutes route;


    public void newRoute(Route value){
        this.route.save(value);
    }
    public List<Cabin>getAll(){
       return this.route.findAll();
    }

    public Route getRouteById(int id){

        return this.route.findById(id);
    }
    public void removeRoute(int id){

        this.route.deleteById(id);
    }


}
