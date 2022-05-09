package com.games.firmaGames.Controller;

import java.util.List;

import com.games.firmaGames.Model.ShoppingCart;
import com.games.firmaGames.Repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartRepository repository;
    
    @Operation(summary = "Find all carts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCart.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisition Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCart.class)) })
    })

    @GetMapping
    public ResponseEntity<List<ShoppingCart>> findAllCarts() {
        return ResponseEntity.ok(repository.findAll());
}

@Operation(summary = "Find Cart By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCart.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCart.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisition Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCart.class)) })
    })
  
    @PostMapping
    public ResponseEntity<ShoppingCart> post(@RequestBody ShoppingCart cart) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cart));
    }

    @Operation(summary = "Update Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCart.class)) }),
            @ApiResponse(responseCode = "400", description = "Not Change", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCart.class)) }),
    })

    @PutMapping
    public ResponseEntity<ShoppingCart> put(@RequestBody ShoppingCart cart) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(cart));
    }

    
    @Operation(summary = "Delete Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully delete ", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCart.class)) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCart.class)) })
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}

