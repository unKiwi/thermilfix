<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Intervention extends Model
{
    use HasFactory;

    protected $fillable = ['intervention', 'name'];

    /**
     * Return create validation rules
     * @return array
     */
    public static function createRules()
    {
        return [
            'intervention' => 'required',
            'name' => 'required',
        ];
    }
}
