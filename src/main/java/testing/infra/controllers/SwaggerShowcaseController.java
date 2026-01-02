package testing.infra.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Swagger/OpenAPI Showcase Controller
 *
 * Este controlador demuestra cómo documentar una API REST usando anotaciones de
 * OpenAPI 3.
 */
@RestController
@RequestMapping("/api/showcase/swagger")
@Tag(name = "Swagger Showcase", description = "Endpoints de ejemplo para demostrar la documentación automática")
public class SwaggerShowcaseController {

    @Value
    @Builder
    @Schema(description = "Modelo de ejemplo para la documentación")
    public static class ShowcaseItem {
        @Schema(description = "Identificador único", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id;

        @Schema(description = "Nombre del ítem", example = "Producto Pro")
        String name;

        @Schema(description = "Precio del ítem", example = "99.99")
        double price;
    }

    @Operation(summary = "Obtener todos los ítems", description = "Retorna una lista de ítems de ejemplo generados estáticamente")
    @GetMapping
    public List<ShowcaseItem> getAll() {
        return List.of(
                ShowcaseItem.builder().id(UUID.randomUUID()).name("Item 1").price(10.0).build(),
                ShowcaseItem.builder().id(UUID.randomUUID()).name("Item 2").price(20.0).build());
    }

    @Operation(summary = "Obtener ítem por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ítem encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ShowcaseItem.class)) }),
            @ApiResponse(responseCode = "404", description = "Ítem no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ShowcaseItem> getById(
            @Parameter(description = "ID del ítem a buscar") @PathVariable UUID id) {
        return ResponseEntity.ok(ShowcaseItem.builder().id(id).name("Item dinámico").price(50.0).build());
    }

    @Operation(summary = "Crear un nuevo ítem")
    @PostMapping
    public ShowcaseItem create(@RequestBody ShowcaseItem item) {
        return item;
    }
}
