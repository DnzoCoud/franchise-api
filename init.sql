-- =========================
-- TABLE: franchise
-- =========================
CREATE TABLE franchise (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(150) NOT NULL UNIQUE,
   created_at TIMESTAMP NOT NULL DEFAULT NOW(),
   updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- =========================
-- TABLE: branch
-- =========================
CREATE TABLE branch (
    id BIGSERIAL PRIMARY KEY,
    franchise_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_branch_franchise
        FOREIGN KEY (franchise_id)
            REFERENCES franchise(id)
            ON DELETE CASCADE,

    CONSTRAINT uq_branch_name_per_franchise
        UNIQUE (franchise_id, name)
);

-- =========================
-- TABLE: product
-- =========================
CREATE TABLE product (
     id BIGSERIAL PRIMARY KEY,
     branch_id BIGINT NOT NULL,
     name VARCHAR(150) NOT NULL,
     stock INTEGER NOT NULL CHECK (stock >= 0),
     created_at TIMESTAMP NOT NULL DEFAULT NOW(),
     updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

     CONSTRAINT fk_product_branch
         FOREIGN KEY (branch_id)
             REFERENCES branch(id)
             ON DELETE CASCADE,

     CONSTRAINT uq_product_name_per_branch
         UNIQUE (branch_id, name)
);

-- =========================
-- INDEXES
-- =========================

CREATE INDEX idx_branch_franchise_id
    ON branch(franchise_id);

CREATE INDEX idx_product_branch_id
    ON product(branch_id);

CREATE INDEX idx_product_branch_stock
    ON product(branch_id, stock DESC);