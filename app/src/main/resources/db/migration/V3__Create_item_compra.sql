-- Criar a tabela de itens de compra
CREATE TABLE item_compra (
    id BIGSERIAL PRIMARY KEY,
    compra_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT NOT NULL CHECK (quantidade > 0),
    preco_unitario NUMERIC(10,2) NOT NULL CHECK (preco_unitario >= 0),
    subtotal NUMERIC(10,2) GENERATED ALWAYS AS (quantidade * preco_unitario) STORED,
    CONSTRAINT fk_item_compra_compra FOREIGN KEY (compra_id) REFERENCES compras(id) ON DELETE CASCADE,
    CONSTRAINT fk_item_compra_produto FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

-- Remover coluna produto_id da tabela compras, se ainda existir
ALTER TABLE compras DROP COLUMN IF EXISTS produto_id;

-- Atualizar tabela compras para somar automaticamente os valores dos itens
ALTER TABLE compras ADD COLUMN IF NOT EXISTS valor_da_compra NUMERIC(10,2) DEFAULT 0;
