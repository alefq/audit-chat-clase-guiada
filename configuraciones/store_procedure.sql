CREATE FUNCTION calcular_iva(precio float, OUT iva float) AS $$
BEGIN
    iva := precio / 11;
END;
$$ LANGUAGE plpgsql;