package com.abnamro.assignment.recipeapp.controller;

import com.abnamro.assignment.recipeapp.dto.BaseResponse;
import com.abnamro.assignment.recipeapp.dto.IngredientDTO;
import com.abnamro.assignment.recipeapp.dto.RecipeDTO;
import com.abnamro.assignment.recipeapp.service.RecipeService;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.google.gson.Gson;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Api(value = "User management", tags = "These services are to add user, grant user, activate services, and ...")
@RestController
@RequestMapping("/api/v1")
public class RecipeController {
    private static Log logger = LogFactory.getLog(RecipeController.class);

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @ApiOperation(value = "List all of recipes.", response = String.class)
    @GetMapping(value ="/list-all-recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public String listAllRecipe() {
        return new Gson().toJson(recipeService.listAllRecipe());
    }

    @ApiOperation(value = "Get a recipe by recipeId", response = String.class)
    @GetMapping(value="/find-recipe-by-vegetarian", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findRecipeByVegetarian(@RequestParam("vegetarian") boolean vegetarian) {
        List<RecipeDTO> recipeDTOList = recipeService.findRecipeByVegetarian(vegetarian);
        return new Gson().toJson(recipeDTOList);
    }

    @GetMapping(value="/find-recipe-by-instruction", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findRecipeByInstruction(@RequestParam("search-text") String text) {
        List<RecipeDTO> recipeDTOList = recipeService.findRecipeByInstruction(text);
        return new Gson().toJson(recipeDTOList);
    }
    
    @GetMapping(value="/find-recipe-by-no-of-servings", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findRecipeByServing(@RequestParam("no-of-servings") Integer noOfServings) {
    	List<RecipeDTO> recipeDTOList = recipeService.findRecipeByServing(noOfServings);
    	return new Gson().toJson(recipeDTOList);
    }
    
    @GetMapping(value="/find-recipe-by-ingredient", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findRecipeContainingIngredients(@RequestBody Set<IngredientDTO> ingredientDTO) {
        List<RecipeDTO> recipeDTOList = recipeService.findRecipeContainingIngredients(ingredientDTO);
        return new Gson().toJson(recipeDTOList);
    }
    
    @GetMapping("/search/findRecipeByIncludingIngredients")
    public List<RecipeDTO> findRecipeByIngredientsIncludes(@RequestParam("includes")  String include)
    				throws NotFoundException {
    	List<String> includes = new ArrayList<>();
    	includes.add(include);
        return recipeService.getByIngredientsIncludes(includes);
    }
    
    @GetMapping("/search/findRecipeByExcludingIngredients")
    public List<RecipeDTO> findRecipeByIngredientsExcludes(@RequestParam("excludes")  String exclude)
    		throws NotFoundException {
    	List<String> excludes = new ArrayList<>();
    	excludes.add(exclude);
    	return recipeService.getByIngredientsExcludes(excludes);
    }

    @GetMapping("/search/findRecipeByInstructionContainsText/{text}")
    public List<RecipeDTO> findRecipeByInstructionContainsText(@PathVariable String text) throws NotFoundException {
        return recipeService.findDistinctByDescriptionContainsAllIgnoreCaseOrderByNameAsc(text);
    }

    @ApiOperation(value = "Add a new recipe", response = String.class)
    @PostMapping(value = "/add-recipe", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addRecipe(@RequestBody RecipeDTO recipeDTO) {

        recipeService.saveRecipe(recipeDTO);
        return "recipe saved successfully.";
    }

    @ApiOperation(value = "Update a recipe", response = String.class)
    @PostMapping(value = "/update-recipe", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateRecipe(@RequestBody RecipeDTO recipeDTO) throws NotFoundException {
        RecipeDTO recipeD = recipeService.findRecipeById(recipeDTO.getRecipeId());
        if (recipeD != null) {
            recipeService.saveRecipe(recipeDTO);
            return "recipe updated successfully.";
        } else {
            return "There is not any recipe with id: " + recipeDTO.getRecipeId();
        }
    }

    @ApiOperation(value = "Remove a recipe", response = String.class)
    @GetMapping("/remove-recipe")
    public String deleteRecipe(@RequestParam("recipe-id") long recipeId) {
        int result = recipeService.removeRecipeById(recipeId);
        if (result > 0)
            return "recipe with id: " + recipeId + " removed.";
        else
            return "there is no recipe with id: " + recipeId;

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleError (HttpServletRequest req, Exception exception) {
        if (exception instanceof ClassCastException) {
            return new ResponseEntity<>(new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    exception.getMessage(), req.getMethod() + " - " + req.getRequestURI(), req.getRemoteUser()),
                    HttpStatus.NOT_ACCEPTABLE);
        } else if (exception instanceof ArrayIndexOutOfBoundsException) {
            return new ResponseEntity<>(new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    exception.getMessage(), req.getMethod() + " - " + req.getRequestURI(), req.getRemoteUser()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
        logger.error("Exception Request[" + req.getRequestURI() + "] raised " + exception, exception);
        return new ResponseEntity<>(new BaseResponse("0",exception.getMessage(),
                req.getMethod() + " - " + req.getRequestURI(), ""), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<BaseResponse> handleMediaTypeError(HttpMediaTypeNotSupportedException exception) {
        logger.debug("Request processed: Response=media.error");
        return new ResponseEntity<>(new BaseResponse("media.error", exception.getMessage(),
                exception.getLocalizedMessage(), Objects.requireNonNull(exception.getContentType()).getType()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse> handleNotFoundException(HttpMediaTypeNotSupportedException exception) {
        logger.debug("Request processed: Response=media.error");
        return new ResponseEntity<>(new BaseResponse("media.error", exception.getMessage(),
                exception.getLocalizedMessage(), Objects.requireNonNull(exception.getContentType()).getType()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleBindingError(MethodArgumentNotValidException exception) {
        logger.info("Request processed: Response=validation.error");
        logger.info(exception.getBindingResult().getFieldError().getField() + " "
                + exception.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(new BaseResponse("validation.error", exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse> handleJsonError(HttpMessageNotReadableException exception) {
        String cause = "incorrect.post.values";
        if (exception.getCause() instanceof UnrecognizedPropertyException)
            cause = "unknown.property." + ((UnrecognizedPropertyException) exception.getCause()).getPropertyName();
        logger.debug("Request processed: Response=" + cause);
        return new ResponseEntity<>(new BaseResponse("httpBadRequest", exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
